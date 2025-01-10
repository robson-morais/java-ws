package br.com.rocketseat.todolist.tasks;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create (@RequestBody Task task, HttpServletRequest request) {
        System.out.println("<< Chegou no controller: " + request.getAttribute("idUser"));

        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(task.getStartAt()) || task.getEndAt().isBefore(currentDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio/término deve ser maior do que a data atual");
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio não pode ser posterior à data de término.");
        }
        var newTask = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<Task> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }
}

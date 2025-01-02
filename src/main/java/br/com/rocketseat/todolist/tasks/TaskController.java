package br.com.rocketseat.todolist.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public Task create (@RequestBody Task task) {
        System.out.println("<< Chegou no controller >>");
        var newTask = this.taskRepository.save(task);
        return newTask;
    }
}

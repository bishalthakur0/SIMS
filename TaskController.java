@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        String taskId = taskService.enqueueTask(task);
        return ResponseEntity.ok("Task ID: " + taskId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }
}

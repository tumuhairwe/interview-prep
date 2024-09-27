package com.tumuhairwe.prep;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2590 (medium)
 *
 * Design a Todo List Where users can add tasks, mark them as complete, or get a list of pending tasks. Users can also add tags to tasks and can filter the tasks by certain tags.
 *
 * Implement the TodoList class:
 *
 * TodoList() Initializes the object.
 * - int addTask(int userId, String taskDescription, int dueDate, List<String> tags) Adds a task for the user with the ID userId with a due date equal to dueDate and a list of tags attached to the task. The return value is the ID of the task. This ID starts at 1 and is sequentially increasing. That is, the first task's id should be 1, the second task's id should be 2, and so on.
 * - List<String> getAllTasks(int userId) Returns a list of all the tasks not marked as complete for the user with ID userId, ordered by the due date. You should return an empty list if the user has no uncompleted tasks.
 * - List<String> getTasksForTag(int userId, String tag) Returns a list of all the tasks that are not marked as complete for the user with the ID userId and have tag as one of their tags, ordered by their due date. Return an empty list if no such task exists.
 * - void completeTask(int userId, int taskId) Marks the task with the ID taskId as completed only if the task exists and the user with the ID userId has this task, and it is uncompleted.
 *
 * ref: https://leetcode.com/problems/design-a-todo-list/
 */
public class TodoList {
    private class Task{
        int id;
        String description;
        int dueDate;
        boolean isComplete;
        List<String> tags;
        public Task(int id, String desc, int dueDate){
            this.id = id;
            this.description = desc;
            this.dueDate = dueDate;

            this.tags = new ArrayList<>();
            this.isComplete = false;
        }
    }

    private Map<Integer, List<Task>> tasks_by_userId;
    private int latestTaskId;

    public TodoList(){
        tasks_by_userId = new HashMap<>();
        latestTaskId = 0;
    }
    public int addTask(int userID, String description, int dueDate, List<String> tags){
        latestTaskId++;
        Task task = new Task(latestTaskId, description, dueDate);
        task.tags = tags;

        List<Task> tasks = tasks_by_userId.getOrDefault(userID, new ArrayList<>());
        tasks.add(task);

        tasks_by_userId.put(userID, tasks);
        return latestTaskId;
    }
    public List<String> getAllTask(int userId){
        List<Task> tasks = tasks_by_userId.getOrDefault(userId, new ArrayList<>());
        return tasks.stream()
                .filter(t -> !t.isComplete)
                .sorted(Comparator.comparingInt(t -> t.dueDate))
                .map(t -> t.description)
                .collect(Collectors.toList());
    }
    public List<String> getTasksForTag(int userId, String tag){
        List<String> tasks = tasks_by_userId.get(userId)
                .stream()
                .filter(t -> !t.isComplete)
                .filter(t -> t.tags.contains(tag))
                .sorted(Comparator.comparingInt(t -> t.dueDate))
                .map(t -> t.description)
                .collect(Collectors.toList());
        return tasks;
    }
    public void completeTask(int userId, int taskId){
        List<Task> tasks = tasks_by_userId.getOrDefault(userId, new ArrayList<>());
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).id == taskId){
                tasks.get(i).isComplete = true;
                break;
            }
        }

        tasks_by_userId.put(userId, tasks);
    }
}

import { Injectable } from '@angular/core';
import { Task, CreateTaskInput, UpdateTaskInput, TaskStatus } from '../models/task';
import { Observable, of, throwError } from 'rxjs';
import { delay } from 'rxjs/operators';
import { v4 as uuidv4 } from "uuid";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private readonly TASKS_STORAGE_KEY = 'angular_task_manager_tasks';

  constructor() { }

  // Helper to get tasks from localStorage
  private getStoredTasks(): Task[] {
    const tasksJson = localStorage.getItem(this.TASKS_STORAGE_KEY);
    if (!tasksJson) return [];
    try {
      return JSON.parse(tasksJson);
    } catch (error) {
      console.error('Failed to parse tasks from localStorage:', error);
      return [];
    }
  }

  // Helper to save tasks to localStorage
  private saveTasksToStorage(tasks: Task[]): void {
    localStorage.setItem(this.TASKS_STORAGE_KEY, JSON.stringify(tasks));
  }

  // Get all tasks
  getAllTasks(): Observable<Task[]> {
    // Simulate API delay
    return of(this.getStoredTasks()).pipe(delay(300));
  }

  // Get a single task by ID
  getTaskById(id: string): Observable<Task> {
    const tasks = this.getStoredTasks();
    const task = tasks.find(task => task.id === id);

    if (!task) {
      return throwError(() => new Error(`Task with ID ${id} not found`));
    }

    // Simulate API delay
    return of(task).pipe(delay(300));
  }

  // Create a new task
  createTask(input: CreateTaskInput): Observable<Task> {
    const now = new Date().toISOString();
    const newTask: Task = {
      id: uuidv4(),
      ...input,
      createdAt: now,
      updatedAt: now
    };

    const tasks = this.getStoredTasks();
    tasks.push(newTask);
    this.saveTasksToStorage(tasks);

    // Simulate API delay
    return of(newTask).pipe(delay(300));
  }

  // Update an existing task
  updateTask(id: string, input: UpdateTaskInput): Observable<Task> {
    const tasks = this.getStoredTasks();
    const taskIndex = tasks.findIndex(task => task.id === id);

    if (taskIndex === -1) {
      return throwError(() => new Error(`Task with ID ${id} not found`));
    }

    const updatedTask: Task = {
      ...tasks[taskIndex],
      ...input,
      updatedAt: new Date().toISOString()
    };

    tasks[taskIndex] = updatedTask;
    this.saveTasksToStorage(tasks);

    // Simulate API delay
    return of(updatedTask).pipe(delay(300));
  }

  // Delete a task
  deleteTask(id: string): Observable<void> {
    const tasks = this.getStoredTasks();
    const filteredTasks = tasks.filter(task => task.id !== id);

    if (filteredTasks.length === tasks.length) {
      return throwError(() => new Error(`Task with ID ${id} not found`));
    }

    this.saveTasksToStorage(filteredTasks);

    // Simulate API delay
    return of(undefined).pipe(delay(300));
  }
}

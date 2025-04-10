import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from './models/task';
import { AuthService } from './auth.service';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private baseUrl = 'http://localhost:8080/api/v1/task';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders() {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
  }

  // Get all tasks
  getTasks(): Observable<Task[]> {
    const headers = this.getHeaders();
    return this.http.get<Task[]>(this.baseUrl, { headers });
  }

  // Get a specific task by ID
  getTask(id: string): Observable<Task> {
    const headers = this.getHeaders();
    return this.http.get<Task>(`${this.baseUrl}/${id}`, { headers });
  }

  // Create a new task
  createTask(task: Task): Observable<Task> {
    const headers = this.getHeaders();
    const taskData = {
      ...task,
      id: task.id || '', // Ensure the ID exists
      createdAt: new Date().toISOString() // Set createdAt to the current time in ISO format
    };
    return this.http.post<Task>(this.baseUrl, taskData, { headers });
  }

  // Update a task
  updateTask(task: Task): Observable<Task> {
    const headers = this.getHeaders();
    const taskData = {
      ...task,
      createdAt: task.createdAt || new Date().toISOString(), // Ensure createdAt is included if not provided
    };
    return this.http.put<Task>(`${this.baseUrl}/${task.id}`, taskData, { headers });
  }

  // Delete a task
  deleteTask(id: string): Observable<void> {
    const headers = this.getHeaders();
    return this.http.delete<void>(`${this.baseUrl}/${id}`, { headers });
  }

  // Method to get the latest task ID by fetching all tasks
  getLatestTaskId(): Observable<string> {
    const headers = this.getHeaders();
    return this.http.get<Task[]>(this.baseUrl, { headers }).pipe(
      map((tasks: Task[]) => {
        // Sort tasks by ID in descending order, using parseInt to convert the ID string to a number
        const latestTask = tasks.sort((a: Task, b: Task) => {
          // Ensure 'a.id' and 'b.id' are not undefined before accessing
          if (a.id && b.id) {
            const aId = parseInt(a.id.replace('T-', ''), 10);
            const bId = parseInt(b.id.replace('T-', ''), 10);
            return bId - aId; // Sort in descending order
          }
          return 0; // Default return if ids are missing (could add error handling here)
        })[0]; // Get the first task (the latest task)

        // If latestTask is found, generate the next ID, else return 'T-0001'
        if (latestTask && latestTask.id) {
          const lastNumber = parseInt(latestTask.id.replace('T-', ''), 10);
          const newNumber = lastNumber + 1;
          return `T-${newNumber.toString().padStart(4, '0')}`;
        }

        // Return 'T-0001' if there are no tasks
        return 'T-0001';
      })
    );
  }
}

import { Injectable } from '@angular/core';
import { Task } from './tasks/task';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

const apiUrl = 'http://localhost:8080/api/tasks';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(apiUrl + '/')
      .pipe(
        tap(_ => this.log('fetched Task')),
        catchError(this.handleError('getTask', []))
      );
  }

  addTask(data:any): Observable<Task[]> {
    return this.http.post<Task[]>(apiUrl + '/',data)
      .pipe(
        tap(_ => this.log('aded Task')),
        catchError(this.handleError('addTask', []))
      );
  }

  deleteTask(data:any) {
    return this.http.delete(apiUrl + '/' + data)
      .pipe(
        tap(_ => this.log('Task deleted')),
        catchError(this.handleError('deleteTask', []))
      );
  }

  updateTask(id:number, data:any): Observable<Task[]> {
    return this.http.put<Task[]>(apiUrl + '/' + id, data)
      .pipe( 
        tap(_ => this.log('Task updated')),
        catchError(this.handleError('updateTask', []))
      );
  }

  shareTask(id:number,data:any): Observable<Task[]> {
    return this.http.post<Task[]>(apiUrl + '/' + id, data)
      .pipe(
        tap(_ => this.log('Task shared')),
        catchError(this.handleError('shareTask', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}

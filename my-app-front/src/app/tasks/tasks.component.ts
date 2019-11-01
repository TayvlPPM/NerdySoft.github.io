import { Component, OnInit } from '@angular/core';
import { Task } from './task';
import { TaskService } from '../task.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  addForm: FormGroup;
  data: Task[] = [];
  displayedColumns: string[] = ['capture', 'sharedBy'];
  isLoadingResults = true;

  constructor(private formBuilder: FormBuilder, private taskService: TaskService, private authService: AuthService, private router: Router) { }

  getTasks(): void {
    this.taskService.getTasks()
      .subscribe(tasks => {
        this.data = tasks;
        console.log(this.data);
        this.isLoadingResults = false;
      }, err => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

  delTask(data:any): void {
    this.taskService.deleteTask(data.id).subscribe(res => {
    this.getTasks();
    }, (err) => {
      console.log(err);
      alert(err.error);
    });
  }

  updateTask(data:any): void {
    console.log(data);
   // this.taskService.updateTask(data).subscribe(res => {
    this.getTasks();
   // }, (err) => {
    //  console.log(err);
    //  alert(err.error);
   // });
  }

  onFormSubmit(form: NgForm) {
    this.taskService.addTask(form).subscribe(res => {
    this.getTasks();
    this.addForm.reset();
    }, (err) => {
      console.log(err);
      alert(err.error);
    });
  }
  ngOnInit() {
    this.addForm = this.formBuilder.group({
      'capture' : [null, Validators.required]
    });
    this.getTasks();
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['signin']);
  }
  
}

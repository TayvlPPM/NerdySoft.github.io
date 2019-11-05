import { Component, OnInit } from '@angular/core';
import { Task } from './task';
import { TaskService } from '../task.service';
import { Router } from '@angular/router';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { NgxSmartModalService } from 'ngx-smart-modal';



@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TasksComponent implements OnInit {
  addForm: FormGroup;
  updateForm: FormGroup;
  shareForm: FormGroup;
  dataSource: Task[] = [];
  displayedColumns: string[] = ['options','caption', 'sharedBy'];
  isLoadingResults = true;
  taskId:any;
 

  constructor(public ngxSmartModalService: NgxSmartModalService, private formBuilder: FormBuilder, private taskService: TaskService, private router: Router) { }

  getTasks(): void {
    this.taskService.getTasks()
      .subscribe(tasks => {
        this.dataSource = tasks;
        console.log(this.dataSource);
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

  chooseTask(data:any): number{
    this.taskId=data.id;
    console.log(this.taskId);
    return this.taskId;
  }

  updateTask(data:NgForm) {
    this.taskService.updateTask(this.taskId,data).subscribe(res => {
      this.updateForm.reset();
      this.getTasks();
      }, (err) => {
        console.log(err);
        alert(err.error);
      });
  }

  shareTask(data:NgForm) {
    console.log(this.taskId,data);
    this.taskService.shareTask(this.taskId,data).subscribe(res => {
      this.shareForm.reset();
      this.getTasks();
      }, (err) => {
        console.log(err);
        alert(err.error);
      });
  }

  addTask(data: NgForm) {
    this.taskService.addTask(data).subscribe(res => {
      this.addForm.reset();
      this.getTasks();
    }, (err) => {
      console.log(err);
      alert(err.error);
    });
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      'capture' : [null, Validators.required]
    });
    this.updateForm = this.formBuilder.group({
      'new_caption' : [null, Validators.required]
    });
    this.shareForm = this.formBuilder.group({
      'shareToEmail' : [null, Validators.required]
    });
    this.getTasks();
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['signin']);
  }
  
}

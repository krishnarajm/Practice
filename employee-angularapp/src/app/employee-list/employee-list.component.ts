import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { Observable } from "rxjs";
import { EmployeeService } from "../employee.service";
import { Employee } from "../employee";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';


@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
    employees: Observable<Employee[]>;

  constructor(private employeeService :EmployeeService,private router:Router) { }

  ngOnInit() {
    this.loadHomeScreen();
  }

  loadHomeScreen(){
    this.employees = this.employeeService.getEmployeesList();
  }

  deleteEmployee(id:number){
    this.employeeService.deleteEmployee(id)
        .subscribe(data => { this.loadHomeScreen();}),
        error => console.log(error);
  }
  employeeDetails(id:number){
    this.router.navigate(['details',id]);
  }
  updateDetails(id:number){
    this.router.navigate(['update',id]);
  }
}

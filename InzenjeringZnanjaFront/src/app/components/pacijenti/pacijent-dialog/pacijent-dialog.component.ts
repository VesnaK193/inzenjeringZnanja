import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pacijent-dialog',
  templateUrl: './pacijent-dialog.component.html',
  styleUrls: ['./pacijent-dialog.component.css']
})
export class PacijentDialogComponent implements OnInit {
  firstname:string="";
  lastname:string="";
  constructor() { }

  ngOnInit() {
  }

  onSubmit(){
    console.log("Ime: " + this.firstname + " , Prezime: "+this.lastname);
  }
  validateSubmit(){
    return this.firstname=="" || this.lastname==""? false:true;
  }
}

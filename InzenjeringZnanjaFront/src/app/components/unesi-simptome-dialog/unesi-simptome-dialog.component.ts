import { Test } from './../../modeli/test';
import { Pregled } from './../../modeli/pregled';
import { ZdravstveniKarton } from './../../modeli/zdravstveni-karton';
import { Pacijent } from './../../modeli/pacijent';
import { Simptom } from './../../modeli/simptom';
import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-unesi-simptome-dialog',
  templateUrl: './unesi-simptome-dialog.component.html',
  styleUrls: ['./unesi-simptome-dialog.component.css']
})
export class UnesiSimptomeDialogComponent implements OnInit {

  selectedOptions = [];
  selectedOption: any;

  listOfSymptomsFromDatabase: Simptom[] = [];
  pacijentSimptomi: Simptom[] = [];

  listOfTests: Test[] = [];
  selectedTests: Test[] = [];
                      
  pacijent: Pacijent;
  karton: ZdravstveniKarton = new ZdravstveniKarton();
  pregled: Pregled = new Pregled();

  constructor(public dialogRef: MatDialogRef<UnesiSimptomeDialogComponent>, @Inject(MAT_DIALOG_DATA) public model: any,
                private http: HttpClient) { }

  ngOnInit() {
    let simptoms = this.dobaviSimptome().subscribe(
      data => {
        this.listOfSymptomsFromDatabase = data;
      }
    );
  }
  
  onNgModelChange(event){
    //console.log(event);
    this.pacijentSimptomi = event;
    //this.listOfTests = event;
    //console.log(this.pacijentSimptomi)
  }
  onNgModelChangeTest(event){
    //console.log(event);
    //this.pacijentSimptomi = event;
    //console.log(this.pacijentSimptomi)
  }

  dodajSimptomeCBR(){
    this.pacijent = this.model;
    this.pregled.simptomi = this.pacijentSimptomi;
    //this.pacijent.karton.pregledi.push(this.pregled);
    this.upisiSimptomeCBR(this.pregled, this.pacijent.karton.id).subscribe(
      data => {
        this.listOfTests = data;
      }
    );
  }
  dodajSimptomeRBR(){
    this.pacijent = this.model;
    this.pregled.simptomi = this.pacijentSimptomi;
    //this.pacijent.karton.pregledi.push(this.pregled);
    this.upisiSimptomeRBR(this.pregled, this.pacijent.karton.id).subscribe(
      data => {
        this.listOfTests = data;
      }
    );
  }


  upisiSimptomeCBR(newModel, id:number):Observable<any>{
    return this.http.post('http://localhost:8089/pregled/' + id, newModel);
  }

  upisiSimptomeRBR(newModel, id:number):Observable<any>{
    return this.http.post('http://localhost:8089/pregled/rbr/' + id, newModel);
  }

  // odabranSimptom(simpt: Simptom){
  //   //alert('Odabran simptom! ' + simpt);
  //   //let temp = this.listOfSymptomsFromDatabase.find(simpt);
  //   let temp2;
  //   console.log(simpt.naziv);
  //   for(let temp of this.listOfSymptomsFromDatabase){
  //     if(temp.naziv === simpt.naziv){
  //       //temp2 = temp;
  //       this.secondListOfSymptoms.push(temp);
  //       console.log(temp.naziv);
  //       this.listOfSymptomsFromDatabase.splice(this.listOfSymptomsFromDatabase.indexOf(temp), 1);
  //     }
  //   }
    
  // }

  dobaviSimptome(): Observable<Simptom[]>{
    return this.http.get<Simptom[]>('http://localhost:8089/pregled/simptom');
  }
}

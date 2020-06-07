import { Component, OnInit, Inject } from '@angular/core';
import { Lek } from 'src/app/modeli/lek.model';
import { Dijagnoza } from 'src/app/modeli/dijagnoza.model';
import { Pacijent } from 'src/app/modeli/pacijent';
import { ZdravstveniKarton } from 'src/app/modeli/zdravstveni-karton';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Test } from 'src/app/modeli/test';

@Component({
  selector: 'app-unesi-testove-dialog',
  templateUrl: './unesi-testove-dialog.component.html',
  styleUrls: ['./unesi-testove-dialog.component.css']
})
export class UnesiTestoveDialogComponent implements OnInit {

  selectedOptions = [];
  selectedOption: any;

  listaSvihTestova: Test[] = [];
  odbraniTestovi: Test[] = [];

  listaDijagnoza: Dijagnoza[] = [];
  odabraneDijagnoze: Dijagnoza[] = [];
                      
  pacijent: Pacijent;
  karton: ZdravstveniKarton = new ZdravstveniKarton();

  constructor(public dialogRef: MatDialogRef<UnesiTestoveDialogComponent>, @Inject(MAT_DIALOG_DATA) public model: any,
  private http: HttpClient) {
    this.dobaviTestove().subscribe(
      data => {
        this.listaSvihTestova = data;
      }
    );
   }

   ngOnInit() {
  }

  onNgModelChange(event){
    this.odbraniTestovi = event;
  }
  onNgModelChangeLek(event){
  }

  dodajTestove(){
    this.pacijent = this.model;
    this.upisiTestove(this.odbraniTestovi, this.pacijent.karton.id).subscribe(
      data => {
        this.listaDijagnoza = data;
        console.log(this.listaDijagnoza);
      }
    );
  }

  upisiTestove(pregled, id:number):Observable<any>{
    return this.http.post('http://localhost:8089/test/' + id, pregled);
  }

  dobaviTestove(): Observable<Test[]>{
    return this.http.get<Test[]>('http://localhost:8089/test/getAllTestovi');
  }
}

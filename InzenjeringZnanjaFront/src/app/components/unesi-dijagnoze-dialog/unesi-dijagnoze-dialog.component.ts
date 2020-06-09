import { Component, OnInit, Inject } from '@angular/core';
import { Lek } from 'src/app/modeli/lek.model';
import { Dijagnoza } from 'src/app/modeli/dijagnoza.model';
import { Pacijent } from 'src/app/modeli/pacijent';
import { ZdravstveniKarton } from 'src/app/modeli/zdravstveni-karton';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-unesi-dijagnoze-dialog',
  templateUrl: './unesi-dijagnoze-dialog.component.html',
  styleUrls: ['./unesi-dijagnoze-dialog.component.css']
})
export class UnesiDijagnozeDialogComponent implements OnInit {

  selectedOptions = [];
  selectedOption: any;

  listaSvihDijagnoza: Dijagnoza[] = [];
  odbraneDijagnoze: Dijagnoza[] = [];

  listaLekova: Lek[] = [];
  odabraniLekovi: Lek[] = [];
                      
  pacijent: Pacijent;
  karton: ZdravstveniKarton = new ZdravstveniKarton();

  constructor(public dialogRef: MatDialogRef<UnesiDijagnozeDialogComponent>, @Inject(MAT_DIALOG_DATA) public model: any,
  private http: HttpClient) {
    this.dobaviDijagnoze().subscribe(
      data => {
        this.listaSvihDijagnoza = data;
      }
    );
   }

  ngOnInit() {
  }

  onNgModelChange(event){
    this.odbraneDijagnoze = event;
  }
  onNgModelChangeLek(event){
  }

  dodajDijagnoze(){
    this.pacijent = this.model;
    this.upisiDijagnoze(this.odbraneDijagnoze, this.pacijent.karton.id).subscribe(
      data => {
        this.listaLekova = data;
        console.log(this.listaLekova);
      }
    );
  }

  dodajDijagnozeRBR(){
    this.pacijent = this.model;
    this.upisiSimptomeRBR(this.odbraneDijagnoze, this.pacijent.karton.id).subscribe(
      data => {
        this.listaLekova = data;
        console.log("RBR");
        console.log(this.listaLekova);
      }
    );
  }

  upisiDijagnoze(dijagnoze, id:number):Observable<any>{
    return this.http.post('http://localhost:8089/lek/' + id, dijagnoze);
  }
  upisiSimptomeRBR(dijagnoze, id:number):Observable<any>{
    return this.http.post('http://localhost:8089/lek/rbr/' + id, dijagnoze);
  }

  dobaviDijagnoze(): Observable<Dijagnoza[]>{
    return this.http.get<Dijagnoza[]>('http://localhost:8089/lek/getAllDijagnoza');
  }
}

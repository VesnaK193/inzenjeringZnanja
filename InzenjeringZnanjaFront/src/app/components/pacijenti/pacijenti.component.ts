import { Simptom } from './../../modeli/simptom';
import { UnesiSimptomeDialogComponent } from './../unesi-simptome-dialog/unesi-simptome-dialog.component';
import { Pacijent } from './../../modeli/pacijent';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { UnesiDijagnozeDialogComponent } from '../unesi-dijagnoze-dialog/unesi-dijagnoze-dialog.component';
import { UnesiTestoveDialogComponent } from '../unesi-testove-dialog/unesi-testove-dialog.component';

@Component({
  selector: 'app-pacijenti',
  templateUrl: './pacijenti.component.html',
  styleUrls: ['./pacijenti.component.css']
})

export class PacijentiComponent implements OnInit {

  pacijenti: Pacijent[] = [];
  displayedColumns: string[] = ['id', 'name', 'lastname', 'simptomi','testovi','dijagnoze'];
  dataSource = new MatTableDataSource<Pacijent>(this.pacijenti);
  constructor(private http: HttpClient, public dialog: MatDialog) { }

  ngOnInit() {
    let res = this.dobaviPacijente().subscribe(
      data => {
        this.pacijenti = data;
        console.log(data);
        
      }
    );
  }
  
  unesiSimptome(model){
    //alert('Pritisnut dogadjaj ' + id);
    const dialogRef = this.dialog.open(UnesiSimptomeDialogComponent, {data: model});
    dialogRef.afterClosed().subscribe(result => {
      //alert('Pritisnuto zatvaranje!')
    });
  }

  unesiDijagnoze(model){
    const dialogRef = this.dialog.open(UnesiDijagnozeDialogComponent, {data: model});
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  unesiTestove(model){
    const dialogRef = this.dialog.open(UnesiTestoveDialogComponent, {data: model});
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  dobaviPacijente(): Observable<Pacijent[]>{
    return this.http.get<Pacijent[]>('http://localhost:8089/pregled/pacijent');
  }
}

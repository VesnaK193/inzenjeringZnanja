import { Simptom } from './../../modeli/simptom';
import { UnesiSimptomeDialogComponent } from './../unesi-simptome-dialog/unesi-simptome-dialog.component';
import { Pacijent } from './../../modeli/pacijent';
import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { UnesiDijagnozeDialogComponent } from '../unesi-dijagnoze-dialog/unesi-dijagnoze-dialog.component';
import { UnesiTestoveDialogComponent } from '../unesi-testove-dialog/unesi-testove-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pacijenti',
  templateUrl: './pacijenti.component.html',
  styleUrls: ['./pacijenti.component.css']
})

export class PacijentiComponent implements OnInit {

  pacijenti: Pacijent[] = [];
  displayedColumns: string[] = ['id', 'name', 'lastname', 'karton'];
  dataSource = new MatTableDataSource<Pacijent>(this.pacijenti);
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  constructor(private http: HttpClient, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    let res = this.dobaviPacijente().subscribe(
      data => {
        this.pacijenti = data;
        console.log(data);
        this.dataSource = new MatTableDataSource<Pacijent>(this.pacijenti);
        this.dataSource.paginator = this.paginator 
      }
    );
  }

  onKarton(model){
    console.log(model);
    this.router.navigateByUrl('karton/' + model.karton.id);
  }

  onNoviPacijent(){
    alert('Neko treba da uradi dodavanje pacijenata! :)');
  }

  dobaviPacijente(): Observable<Pacijent[]>{
    return this.http.get<Pacijent[]>('http://localhost:8089/pregled/pacijent');
  }
}

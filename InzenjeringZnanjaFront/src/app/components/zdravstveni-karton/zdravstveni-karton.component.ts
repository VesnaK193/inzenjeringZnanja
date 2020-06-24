import { UnesiTestoveDialogComponent } from './../unesi-testove-dialog/unesi-testove-dialog.component';
import { UnesiDijagnozeDialogComponent } from './../unesi-dijagnoze-dialog/unesi-dijagnoze-dialog.component';
import { UnesiSimptomeDialogComponent } from './../unesi-simptome-dialog/unesi-simptome-dialog.component';
import { ZdravstveniKarton } from './../../modeli/zdravstveni-karton';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { Pregled } from './../../modeli/pregled';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-zdravstveni-karton',
  templateUrl: './zdravstveni-karton.component.html',
  styleUrls: ['./zdravstveni-karton.component.css']
})
export class ZdravstveniKartonComponent implements OnInit {

  pregledi: Pregled[] = [];
  displayedColumns: string[] = ['id', 'simptomi','testovi','dijagnoze'];
  dataSource;
  idKartona;
  constructor(private http: HttpClient, private route: ActivatedRoute, public dialog: MatDialog) { }

  ngOnInit() {
    this.idKartona = parseInt(this.route.snapshot.paramMap.get('id'));
    this.dobaviZdravstveneKartone(this.idKartona).subscribe(
      data => {
        console.log(data)
        this.pregledi = data.pregledi;
        this.dataSource = new MatTableDataSource<Pregled>(this.pregledi);
      }
    );
  }

  unesiSimptome(model){
    console.log(model)
    const dialogRef = this.dialog.open(UnesiSimptomeDialogComponent, {data: model});
    dialogRef.afterClosed().subscribe(result => {
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

  onNoviPregled(){
    this.kreirajNoviPregled(this.idKartona).subscribe(
      data => {
        console.log('Uspesno kreiran novi pregled');
        window.location.reload();
      }
    );
  }

  dobaviZdravstveneKartone(id: number):Observable<ZdravstveniKarton>{
    return this.http.get<ZdravstveniKarton>('http://localhost:8089/pregled/karton/' + id);
  }

  kreirajNoviPregled(id: number):Observable<any>{
    return this.http.put('http://localhost:8089/pregled', id);
  }

}
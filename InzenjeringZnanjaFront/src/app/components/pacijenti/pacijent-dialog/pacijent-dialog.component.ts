import { Pacijent } from 'src/app/modeli/pacijent';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pacijent-dialog',
  templateUrl: './pacijent-dialog.component.html',
  styleUrls: ['./pacijent-dialog.component.css']
})
export class PacijentDialogComponent implements OnInit {
  firstname:string="";
  lastname:string="";
  pol: string="";
  rasa: string="";
  pacijent: Pacijent = new Pacijent();
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onSubmit(){
    console.log("Ime: " + this.firstname + " , Prezime: "+this.lastname);
  }
  validateSubmit(){
    return this.firstname=="" || this.lastname==""? false:true;
  }

  unesiPacijent(){
    this.upisiPacijenta().subscribe(
      data => {
        console.log('Pacijent sacuvan!');
      }
    );
  }

  upisiPacijenta(){
    this.pacijent.name = this.firstname;
    this.pacijent.lastname = this.lastname;
    this.pacijent.pol = this.pol;
    this.pacijent.rasa = this.rasa;
    console.log('Pacijent je: ' + this.pacijent);
    return this.http.post('http://localhost:8089/pregled/karton', this.pacijent);
  }
}

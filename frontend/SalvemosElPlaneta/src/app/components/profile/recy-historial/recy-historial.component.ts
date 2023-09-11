import { Component, Input } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-recy-historial',
  templateUrl: './recy-historial.component.html',
  styleUrls: ['./recy-historial.component.css']
})
export class RecyHistorialComponent {

  @Input()userId!:number

}

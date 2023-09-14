import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-filled-button',
  templateUrl: './filled-button.component.html',
  styleUrls: ['./filled-button.component.css']
})
export class FilledButtonComponent {
  @Input()label: String ="";
  @Input()type: String ="";
  @Input()disabled: boolean =false;
  @Input()loader:boolean=false;

}

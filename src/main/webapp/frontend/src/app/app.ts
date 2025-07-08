import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ReactiveFormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

    public submitted: boolean = false;
    roomsearch!: FormGroup;

    ngOnInit(): void {
        this.roomsearch = new FormGroup({
            checkin: new FormControl(''),
            checkout: new FormControl(''),
        })
    }

    onSubmit({value, valid}: {value: RoomSearch, valid: boolean}) {
        console.log(value);
    }

}

export interface RoomSearch {
    checkin: string;
    checkout: string;
}

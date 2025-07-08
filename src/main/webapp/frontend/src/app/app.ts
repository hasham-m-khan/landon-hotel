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
    rooms!: Room[];

    ngOnInit(): void {
        this.roomsearch = new FormGroup({
            checkin: new FormControl(''),
            checkout: new FormControl(''),
        });
        this.rooms = ROOMS;
    }

    onSubmit({value, valid}: {value: RoomSearch, valid: boolean}) {
        console.log(value);
    }

    reserveRoom(value: string) {
        console.log(value);
    }
}

export interface RoomSearch {
    checkin: string;
    checkout: string;
}

export interface Room {
    id: string;
    roomNumber: string;
    price: string;
    links: string;
}

var ROOMS: Room[] = [
    {
        "id": "324131",
        "roomNumber": "403",
        "price": "59",
        "links": ""
    },
    {
        "id": "789456",
        "roomNumber": "254",
        "price": "66",
        "links": ""
    },
    {
        "id": "798465",
        "roomNumber": "486",
        "price": "46",
        "links": ""
    },
    {
        "id": "796412",
        "roomNumber": "45",
        "price": "58",
        "links": ""
    },
];

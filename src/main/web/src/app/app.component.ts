import {Component, OnInit} from '@angular/core';
import {ProfileService} from "./services/profile.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    title = 'myspb';

    constructor(
        private profileService: ProfileService
    ) {
    }

    ngOnInit() {
    }

}

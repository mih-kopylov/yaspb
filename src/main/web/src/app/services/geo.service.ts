import {Injectable} from "@angular/core";
import {isDefined} from "@angular/compiler/src/util";

@Injectable({
    providedIn: "root",
})
export class GeoService {
    private positionError: PositionError;
    private coords: Coordinates;

    constructor() {
        let options: PositionOptions = new class implements PositionOptions {
            enableHighAccuracy: true;
            timeout: 1000;
        };

        navigator.geolocation.watchPosition(position => {
            this.positionError = undefined;
            this.coords = position.coords;
            console.log("got position", this.coords);
        }, positionError => {
            console.error(positionError);
            this.positionError = positionError;
        }, options);
    }

    getCoords() {
        if (this.isError() || !isDefined(this.coords)) {
            throw new Error("Position is not defined");
        }
        return this.coords;
    }

    isError() {
        return isDefined(this.positionError);
    }
}

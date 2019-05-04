export class Reason {
    id: number;
    name: string;
}

export class Category {
    id: number;
    name: string;
    reasons: Reason[];
}

export class CityObject {
    id: number;
    name: string;
    categories: Category[];
}
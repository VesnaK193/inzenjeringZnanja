import { ZdravstveniKarton } from './zdravstveni-karton';
import { Test } from './test';
import { Simptom } from './simptom';
export class Pregled {
    id:number;
    simptomi: Simptom[];
    testovi: Test[];
}

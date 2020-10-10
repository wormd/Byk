import { Client } from './client';
import { Employee } from './employee';
import { Supply } from './supply';

export class Service {
    id: string;
    name: string;
    created: Date;
    staff: Employee[];
    supplies: Supply[];
    clients: Client[];
}

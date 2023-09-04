import { Inventory } from "./inventory"
export interface RewardDTO {
    id:number,
    name:string,
    price:number,
    description:string,
    photo:string,
    inventory?:Inventory
}

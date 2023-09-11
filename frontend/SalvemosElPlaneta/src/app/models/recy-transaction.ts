import { User } from "./user"

export interface RecyTransaction {
    user:User,
    pointsEarned:number,
    totalWeight:number,
    timestamp: Date
}

import { RewardDTO } from "./reward-dto";
import { User } from "./user";

export interface RewardTransaction {
    user:User,
    reward:RewardDTO,
    timestamp:Date
}

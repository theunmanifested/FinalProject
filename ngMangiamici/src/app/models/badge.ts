import { UserBadge } from "./user-badge";

export class Badge {

    id: number;
    name: string;
    description: string;
    imgUrl: string;
    userBadges: UserBadge[];

    constructor(id?: number, name?: string, description?: string, imgUrl?: string,
                userBadges?: UserBadge[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imgUrl = imgUrl;
    this.userBadges = userBadges;
    }
}

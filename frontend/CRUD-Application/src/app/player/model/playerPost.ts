export interface PlayerPost{

    firstName: string;

    lastName: string;

    nationality: string;

    placeOfBirth: string;

    dateOfBirth: Date;

    imageURL: string;

    jerseyNumber: number;

    appearances: number;

    goals: number;

    assists: number;

    club: {
        id: string
    };

}
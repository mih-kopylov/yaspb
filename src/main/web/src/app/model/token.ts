export class Token {
    accessToken: string;
    refreshToken: string;

    static parse(tokenString: string): Token {
        let parts = tokenString.split(":");
        let result = new Token();
        result.accessToken = parts[0];
        result.refreshToken = parts[1];
        return result;
    }

    toHeader(): string {
        return this.accessToken + ":" + this.refreshToken;
    }
}

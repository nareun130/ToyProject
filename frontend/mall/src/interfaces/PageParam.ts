export interface PageParam{
    page:number,
    size?:number// size가 넘어갈 수도 있고 안 넘어갈수도 있다.-> PageComponent, ListCompoent둘다 사용가능
}
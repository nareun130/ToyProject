export interface Todo {
    tno: number,
    title: string,
    writer: string,
    complete: boolean,
    dueDate: string
}

export type TodoInputType = Omit<Todo, 'tno'|'complete'>; // * TODO에서 tno, complete를 제외한 타입
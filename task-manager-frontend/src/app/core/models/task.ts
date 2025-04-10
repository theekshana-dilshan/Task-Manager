export interface Task {
    id?: number;
    title: string;
    description?: string;
    status: 'TO_DO' | 'IN_PROGRESS' | 'DONE';
  }
  
  // src/app/core/models/user.ts
  export interface User {
    id?: number;
    username: string;
    password: string;
  }
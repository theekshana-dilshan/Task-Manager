export interface Task {
    id?: string;
    title: string;
    description?: string;
    status: 'TO_DO' | 'IN_PROGRESS' | 'DONE';
    createdAt: string;
  }
  
  // src/app/core/models/user.ts
  export interface User {
    id?: string;
    username: string;
    password: string;
  }
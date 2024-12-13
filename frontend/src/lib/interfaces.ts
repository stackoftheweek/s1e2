export interface Message {
  textContent: string;
  createdAt: Date;
  source: 'user' | 'client';
}
export interface Message {
  textContent: string;
  createdAt: string;
  source: 'user' | 'client';
}
import { defineStore } from 'pinia'

export const useConversationStore = defineStore('conversation', {
  state: () => {
    return { conversations: [] }
  },
  actions: {
    getById( conversationId: number) {
      return this.conversations.find( conversation => conversation.conversationId === conversationId)
    },
    updateById(conversationId: number, message: string) {
      const conversation = this.conversations.find( conversation => conversation.conversationId === conversationId)
      if (!conversation) {
        this.conversations.push({ conversationId, messages: [message]});
      } else {
        conversation.messages.push()
      }
    }
  },
})
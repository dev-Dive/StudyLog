'use client'

import ReadContent from '@/components/Read'
import React from 'react'
import { QueryClientProvider, QueryClient } from '@tanstack/react-query'

const queryClient = new QueryClient()

export default function Read() {
  return (
    <QueryClientProvider client={queryClient}>
      <ReadContent />
    </QueryClientProvider>
  )
}

import Markdown from 'react-markdown'
import styled from '@emotion/styled'
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter'
import { materialDark } from 'react-syntax-highlighter/dist/cjs/styles/prism'
import remarkGfm from 'remark-gfm'
import Image from 'next/image'

type postType = {
  content: string
}

export default function MarkDownPost({ content }: postType) {
  return (
    <div>
      <style>
        {tableStyles} {blockQuoteStyles}
      </style>
      <Markdown
        remarkPlugins={[remarkGfm]} // Allows us to have embedded HTML tags in our markdown
        components={{
          code({ node, inline, className, children, ...props }) {
            const match = /language-(\w+)/.exec(className || '')
            return !inline && match ? (
              <SyntaxHighlighter
                language={match[1]}
                PreTag="div"
                {...props}
                style={materialDark}
              >
                {String(children).replace(/\n$/, '')}
              </SyntaxHighlighter>
            ) : (
              <code {...props}>{children}</code>
            )
          },
          img: (image) => (
            <StyledMarkDownImg
              src={image.src || ''}
              alt={image.alt || ''}
              width={500}
              height={300}
            />
          ),
          table: ({ node, ...props }) => (
            <table style={{ border: '1px solid black' }} {...props} />
          ),
        }}
      >
        {content}
      </Markdown>
    </div>
  )
}

const StyledMarkDownImg = styled.img`
  width: 100%;
  height: auto;
  max-height: 300px;
  object-fit: contain;
`

const tableStyles = `
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid black;
  padding: 8px;
  text-align: left;
}
`

const blockQuoteStyles = `
blockquote {
  color: #666;
  margin: 0;
  padding-left: 3em;
  border-left: 0.5em #eee solid;
}
`

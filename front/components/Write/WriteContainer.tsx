import InputContextTextArea from '@/components/Write/ContextTextArea'
import MarkDownPost from '@/components/Write/MarkDownPost'
import React, { useState } from 'react'
import {
  WriteWrapper,
  Inputs,
  MarkDownView,
  TagInput,
  TagContainer,
  Tags,
  LeftSide,
} from './write.styles'
import Tag from '@/components/Commmon/Tag'
import { useRecoilState } from 'recoil'
import { postState } from '@/states/postAtom'
import Footer from './Footer'

type TagItem = {
  id: number
  text: string
}

export default function WriteContainer() {
  const [post, setPost] = useRecoilState(postState)
  const [tagInput, setTagInput] = useState('')

  const onChangeTitle = ({ target }: React.ChangeEvent<HTMLInputElement>) => {
    setPost((prevState) => ({
      ...prevState,
      title: target.value,
    }))
  }

  const onChangeContent = ({
    target,
  }: React.ChangeEvent<HTMLTextAreaElement>) => {
    setPost((prevState) => ({
      ...prevState,
      content: target.value,
    }))
  }

  const removeTag = (tagIdToRemove: number) => {
    setPost((prev) => ({
      ...prev,
      tags: prev.tags.filter((tag) => tag.id !== tagIdToRemove),
    }))
  }

  const onPressTagInput = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key !== 'Enter') return

    if (tagInput.trim() !== '') {
      e.preventDefault()
      const inputValue = (e.target as HTMLInputElement).value

      setPost((prev) => ({
        ...prev,
        tags: [...prev.tags, { id: Date.now(), text: inputValue }],
      }))
    }

    setTagInput('')
  }

  return (
    <WriteWrapper>
      <LeftSide>
        <Inputs>
          <input
            type="text"
            placeholder="제목을 입력하세요"
            onChange={onChangeTitle}
          />
          <hr />
          <TagContainer>
            <TagInput
              type="text"
              value={tagInput}
              placeholder="태그를 입력하세요"
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                setTagInput(e.target.value)
              }
              onKeyPress={onPressTagInput}
            />
            <Tags>
              {post.tags?.map((tag) => (
                <Tag
                  key={tag.id}
                  text={tag.text}
                  canRemove={true}
                  onRemove={() => removeTag(tag.id)}
                />
              ))}
            </Tags>
          </TagContainer>
          <InputContextTextArea
            content={post.content}
            onChange={onChangeContent}
          />
        </Inputs>
        <Footer />
      </LeftSide>
      <MarkDownView>
        <MarkDownPost content={post.content} />
      </MarkDownView>
    </WriteWrapper>
  )
}

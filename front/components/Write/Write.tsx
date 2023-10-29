'use client'

import InputContextTextArea from '@/components/Write/ContextTextArea'
import MarkDownPost from '@/components/Write/MarkDownPost'
import React, { useState } from 'react'
import S from './write.styles'
import Tag from '@/components/Commmon/Tag'
import { useRecoilState } from 'recoil'
import { postState } from '@/states/postAtom'
import Footer from './Footer'

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
    <S.WriteWrapper>
      <S.LeftSide>
        <S.Inputs>
          <input
            type="text"
            placeholder="제목을 입력하세요"
            onChange={onChangeTitle}
          />
          <hr />
          <S.TagContainer>
            <S.TagInput
              type="text"
              value={tagInput}
              placeholder="태그를 입력하세요"
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                setTagInput(e.target.value)
              }
              onKeyPress={onPressTagInput}
            />
            <S.Tags>
              {post.tags?.map((tag) => (
                <Tag
                  key={tag.id}
                  text={tag.text}
                  canRemove={true}
                  onRemove={() => removeTag(tag.id)}
                />
              ))}
            </S.Tags>
          </S.TagContainer>
          <InputContextTextArea
            content={post.content}
            onChange={onChangeContent}
          />
        </S.Inputs>
        <Footer />
      </S.LeftSide>
      <S.MarkDownView>
        <MarkDownPost content={post.content} />
      </S.MarkDownView>
    </S.WriteWrapper>
  )
}

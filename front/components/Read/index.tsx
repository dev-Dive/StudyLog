import { getPost } from '@/api/Read/getPost'
import { useQuery } from '@tanstack/react-query'
import Content from './Content'
import PrevNextPost from './PrevNextPost'
import SideBar from './SideBar'

export default function ReadContent() {
  const { data, isLoading, isError, error } = useQuery({
    queryKey: ['post'],
    queryFn: () => getPost(1),
  })
  console.log(data)
  return (
    <div>
      <Content
        title={data.title}
        subtitle={data.subtitle}
        content={data.content}
        date={data.date}
        authors={data.authors}
        thumbnailImageUrl={data.thumbnailImageUrl}
        study={data.study}
      />
      <PrevNextPost />
      <SideBar />
      ㅁㄴㅇㅁ
    </div>
  )
}

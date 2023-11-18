import { getPost } from '@/api/Read/getPost'
import { useQuery } from '@tanstack/react-query'
import Content from './Content'
import PrevNextPost from './PrevNextPost'
import SideBar from './SideBar'

interface PostData {
  title: string
  subtitle: string
  content: string
  date: string
  authors: Author[]
  thumbnailImageUrl: string
  study: {
    id: number
    name: string
    profileImageUrl: string
    description: string
  }
}

interface Author {
  id: number
  name: string
}

export default function ReadContent() {
  const { data, isLoading, isError, error } = useQuery<PostData, Error>({
    queryKey: ['post'],
    queryFn: () => getPost(1),
  })

  if (isLoading) {
    // 데이터 로딩 중일 때의 UI 처리
    return <div>Loading...</div>
  }

  if (isError) {
    // 에러 발생 시의 UI 처리
    return <div>Error: {error?.message}</div>
  }

  if (!data) {
    // data가 없을 때의 UI 처리
    return <div>No data available</div>
  }
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
    </div>
  )
}

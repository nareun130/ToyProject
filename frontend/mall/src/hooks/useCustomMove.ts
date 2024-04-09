import { useState } from "react"
import { createSearchParams, useNavigate, useSearchParams } from "react-router-dom"
import { PageParam } from "../interfaces/PageParam"

export interface TCustomMove {
  moveToList : (param?:PageParam) => void,
  moveToModify:(num:number) => void,
  moveToRead:(num:number) => void,
  page:number,
  size:number,
  refresh:boolean
}

const getNum = (param: string | null, defaultValue : number):number =>{
    if(!param){
        return defaultValue
    }
    return parseInt(param)
}

const useCustomMove = () : TCustomMove => {

    const navigate = useNavigate()

    //* queryString으로 부터 값을 가져옴.
    const [queryParams] = useSearchParams();

    //* 동일 페이지 클릭 시 서버호출을 위해
    const [refresh, setRefresh] = useState<boolean>(false)

    const page : number = getNum(queryParams.get('page'),1)

    const size : number = getNum(queryParams.get('size'),10)

    //* read -> modfiy로 이동 시 queryString유지 하려고
    const queryDefault = createSearchParams({page:page.toString(), size:size.toString()}).toString()

    //* ?: 선택적 매개변수 -> 생략도 가능하다는 뜻
    const moveToList = (pageParam?: PageParam):void => {

        let queryStr = ""
    
        if(pageParam){
    
          const pageNum = pageParam.page || 1
          const sizeNum = pageParam.size || 10
    
          console.log("-----------------")
          console.log(pageNum, sizeNum)
    
          queryStr = createSearchParams({page:pageNum.toString(), size: sizeNum.toString()}).toString()
    
        }else {
          queryStr = queryDefault
        }
        
        setRefresh(!refresh) //추가 
    
        navigate({pathname: `../list`,search:queryStr})
      }
    
      const moveToModify =  (num: number) : void => {
    
        console.log(queryDefault)
    
        navigate({
          pathname: `../modify/${num}`,
          search: queryDefault  //수정시에 기존의 쿼리 스트링 유지를 위해 
        })
      }
    
      const moveToRead = (num:number) => {
    
        console.log(queryDefault)
    
        navigate({
          pathname: `../read/${num}`,
          search: queryDefault
        })
      }

    return {moveToList, page, size, moveToModify, refresh, moveToRead}
}

export default useCustomMove
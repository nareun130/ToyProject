import { PageResponse } from "../../interfaces/PageResponse";
import { Todo } from "../../interfaces/Todo";
import { PageParam } from '../../interfaces/PageParam.ts';

// type MovePageFunction = (param?:PageParam|undefined) => void
interface PageProps{
  serverData : PageResponse<Todo>

  // movePage : (params:PageParam) => void;
  // movePage:Function
  //TODO : 수정 필요 -> 현수형에게 질문
  movePage: (params: { page: number }|PageParam) => void;
  // movePage:(params: { page:number,size?:number }) => void
  // movePage : (params:PageParam) => void
  // movePage:(params:{page:number}) => void;


}


const PageComponent: React.FC<PageProps> = ({serverData, movePage}: PageProps) => {

    return (  
      <div className="m-6 flex justify-center">
  
      {serverData.prev ? 

        <div 
        className="m-2 p-2 w-16 text-center  font-bold text-blue-400 "
        onClick={() => movePage({page:serverData.prevPage} )}>
        Prev </div> : <></>}  
  
        {serverData.pageNumList.map(pageNum => 
        <div 
        key={pageNum}
        className={ `m-2 p-2 w-12  text-center rounded shadow-md text-white ${serverData.current === pageNum? 'bg-gray-500':'bg-blue-400'}`}
        //* page의 size 유지시키려면 movePage에 size속성 넣어야 함. 
        onClick={() => movePage( {page:pageNum})}>
        {pageNum}
        </div>
  
        )}
  
        {serverData.next ? 
        <div 
        className="m-2 p-2 w-16 text-center font-bold text-blue-400"
        onClick={() => movePage( {page:serverData.nextPage})}> 
        Next 
        </div> : <></>}  
  
      </div>   
  
    );
  
  };
  
  export default PageComponent;
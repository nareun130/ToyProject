import { PageParam } from "../../interfaces/PageParam";
import { PageResponse } from "../../interfaces/PageResponse";
import { Todo } from "../../interfaces/Todo";

interface PageProps{
  serverData : PageResponse<Todo>

  // movePage : Function
  //TODO : 수정 필요
  movePage: (params: { page: number }|PageParam) => void;
  // movePage : (params:PageParam) => void

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
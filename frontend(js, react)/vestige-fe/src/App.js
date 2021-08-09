import logo from './logo.svg';
import './App.css';

let a = 10; // 변수
const b = 20; // 상수

function App() {

  let c;
  console.log(1, c);

  return(
    <div>
      <div>리엑트 스터디</div>
      <hr/>
      <h3>let a = {a}</h3>
      <h3>let b = {b}</h3>
      <hr/>
      <h3>a === 10 ? '10입니다.':'10이 아닙니다.'</h3>
      <h3>{a === 10 ? '10입니다.':'10이 아닙니다.'}</h3>
      <hr/>
      <h3>조건부 렌더링 b === 20 && '20입니다.'</h3>
      <h3>{b === 20 && '20입니다.'}</h3>
      <h3>조건부 렌더링 b === 10 && '10입니다.'</h3>
      <h3>{b === 10 && '10입니다.'}</h3>

    </div>
  );
}

export default App;

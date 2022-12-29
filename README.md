# Sparta_Spring_exam
개인과제는 personal_assignment 디렉토리에 있습니다!
![image](https://user-images.githubusercontent.com/118798407/209825771-6f86ff7f-5b06-4a8c-aa64-d6e250e4c561.png)
| Method | URL | Request | Response |
| --- | --- | --- | --- |
| POST | /api/memos | {"nickname":"딸기","pw":"123a", "title":"과일 가게", "comment": "나는 사실 감자입니다."} | {"createdAt": "2022-12-28T22:21:28.4737323","modifiedAt": "2022-12-28T22:21:28.4737323","id": 1,"nickname": "딸기","title": "과일 가게","comment": "나는 사실 감자입니다.","pw": "123a"} |
| GET | /api/memos | - | [{"createdAt": "2022-12-28T19:31:53.48187","modifiedAt": "2022-12-28T19:31:53.48187","id": 1,"nickname": "딸기","title": "과일 가게","comment": "나는 사실 감자입니다.","pw": "123a"}] |
| PUT | /api/memos/{id} | {"nickname":"수박","pw":"123a","title":"야채 가게", "comment": "나는 사실 양파입니다."} | {"nickname": "수박","title": "야채 가게","comment": "나는 사실 양파입니다.","createdAt": "2022-12-28T20:09:12.605167","modifiedAt": "2022-12-28T20:09:12.605167","id": 1} |
| GET | /api/memos/{id} | - | {"nickname": "수박","title": "야채 가게","comment": "나는 사실 양파입니다.","createdAt": "2022-12-28T20:09:12.605167","modifiedAt": "2022-12-28T20:10:47.703559","id": 1} |
| DELETE | /api/memos/{id} | {"pw":"123a"} | 삭제가 완료 되었습니다. |

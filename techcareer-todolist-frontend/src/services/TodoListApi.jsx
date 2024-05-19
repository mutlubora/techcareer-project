import axios from "axios";

const TODO_API_REQUEST_URI = "http://localhost:4444/api/v1/todos";

export function createTodo(todo) {
  return axios.post(TODO_API_REQUEST_URI, todo);
}

export function listTodos(isDone) {
  if (isDone != null) {
    return axios.get(`${TODO_API_REQUEST_URI}?isDone=${isDone}`);
  }
  return axios.get(TODO_API_REQUEST_URI);
}

export function findTodoById(id) {
  return axios.get(`${TODO_API_REQUEST_URI}/${id}`);
}

export function updateTodo(id, todo) {
  return axios.put(`${TODO_API_REQUEST_URI}/${id}`, todo);
}

export function deleteTodoById(id) {
  return axios.delete(`${TODO_API_REQUEST_URI}/${id}`);
}

export function deleteAllTodos() {
  return axios.get(`${TODO_API_REQUEST_URI}/delete/all`);
}

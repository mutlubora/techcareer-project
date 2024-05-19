import React, { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid';
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
import {
  createTodo,
  listTodos,
  findTodoById,
  updateTodo,
  deleteTodoById,
  deleteAllTodos
} from './services/TodoListApi';

const App = () => {
    const [items, setItems] = useState([]);
    const [itemsToShow, setItemsToShow] = useState("all");
    const [item, setItem] = useState('');
    const [editItem, setEditItem] = useState(false);

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await listTodos();
            setItems(response.data);
        } catch (error) {
            console.error("Error fetching todos:", error);
        }
    };

    const handleChange = event => {
        setItem(event.target.value);
    };

    const handleSubmit = async event => {
      event.preventDefault();
      if (item.trim() === '') return; // Boş bir todo eklenmesini engelle
      if (editItem) {
          // Eğer edit moddaysak, mevcut todo'yu güncelle
          try {
              const updatedItem = { ...items.find(todo => todo.id === editItem), content: item };
              await updateTodo(editItem, updatedItem);
              fetchTodos();
              setItem('');
              setEditItem(false);
          } catch (error) {
              console.error("Error updating todo:", error);
          }
      } else {
          // Değilse, yeni bir todo oluştur
          const newItem = {
              content: item,
              done: false,
              systemCreatedDate: new Date()
          };
          try {
              const response = await createTodo(newItem);
              const newTodo = response.data; // Yeni todo'nun id'sini almak için dönen veriden id'yi al
              setItems([...items, newTodo]); // Yeni todo'yu items state'ine ekle
              setItem('');
          } catch (error) {
              console.error("Error creating todo:", error);
          }
      }
  };
  

    const updateTodosToShow = string => {
        setItemsToShow(string);
    };

    const handleDoneTask = async id => {
        const itemToUpdate = items.find(item => item.id === id);
        if (itemToUpdate) {
            const updatedItem = { ...itemToUpdate, done: !itemToUpdate.done };
            try {
                await updateTodo(id, updatedItem);
                fetchTodos();
            } catch (error) {
                console.error("Error updating todo:", error);
            }
        }
    };

    const handleDelete = async id => {
        try {
            await deleteTodoById(id);
            fetchTodos();
        } catch (error) {
            console.error("Error deleting todo:", error);
        }
    };

    const handleEdit = id => {
        const selectedItem = items.find(item => item.id === id);
        if (selectedItem) {
            setItem(selectedItem.content);
            setEditItem(selectedItem.id);
        }
    };

    const handleDeleteDoneTasks = async () => {
        const doneItems = items.filter(item => item.done);
        const deletePromises = doneItems.map(item => deleteTodoById(item.id));
        try {
            await Promise.all(deletePromises);
            fetchTodos();
        } catch (error) {
            console.error("Error deleting done todos:", error);
        }
    };

    const clearList = async () => {
        try {
            await deleteAllTodos();
            fetchTodos();
        } catch (error) {
            console.error("Error clearing todo list:", error);
        }
    };

    let filteredItems = [];
    if (itemsToShow === "all") {
        filteredItems = items;
    } else if (itemsToShow === "todo") {
        filteredItems = items.filter(item => !item.done);
    } else if (itemsToShow === "done") {
        filteredItems = items.filter(item => item.done);
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-10 col-md-8 mx-auto mt-4">
                    <h3 className="text-capitalize text-center">TodoInput</h3>
                    <TodoInput
                        item={item}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        editItem={editItem}
                    />
                    <TodoList
                        items={filteredItems}
                        clearList={clearList}
                        handleDelete={handleDelete}
                        handleEdit={handleEdit}
                        handleDoneTask={handleDoneTask}
                        handleDeleteDoneTasks={handleDeleteDoneTasks}
                        updateTodosToShow={updateTodosToShow}
                    />
                </div>
            </div>
        </div>
    );
};

export default App;

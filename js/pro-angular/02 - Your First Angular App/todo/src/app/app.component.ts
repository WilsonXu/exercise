import { Component } from '@angular/core';
import { Model, TodoItem } from './model';

@Component({
  selector: 'app-todo',
  templateUrl: './app.component.html'
})
export class AppComponent {
  model: Model = new Model();

  getName(): string {
    return this.model.user;
  }

  getTodoItems(): Array<TodoItem> {
    return this.model.items;
    // return this.model.items.filter(item => !item.done);
  }

  addItem(newItem: string): void {
    if (newItem !== '') {
      this.model.items.push(new TodoItem(newItem, false));
    }
  }
}

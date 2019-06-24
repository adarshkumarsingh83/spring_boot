import React, { Component } from "react";

class CategoryPost extends React.Component {
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
    this.formInput = { catName: "", parentCatId: "" };
  }

  handleSubmit(event) {
    var payload = {
      catName: this.formInput.catName,
      parentCatId: this.formInput.parentCatId
    };

    fetch("http://localhost:9090/api/category", {
      method: "POST",
      body: JSON.stringify(payload),
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    });
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label htmlFor="catName">Enter Cat Name</label>
        <input
          id="catName"
          name="catName"
          type="text"
          onChange={event =>
            (this.formInput.catName = event.currentTarget.value)
          }
        />

        <label htmlFor="parentCatId">Enter Parent CatId</label>
        <input
          id="parentCatId"
          name="parentCatId"
          type="text"
          onChange={event =>
            (this.formInput.parentCatId = event.currentTarget.value)
          }
        />

        <br />
        <button>Save category</button>
      </form>
    );
  }
}
export default CategoryPost;

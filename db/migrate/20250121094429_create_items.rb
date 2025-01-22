class CreateItems < ActiveRecord::Migration[8.0]
  def change
    create_table :items do |t|
      t.string :name
      t.decimal :price
      t.integer :stock_quantity

      t.timestamps
    end
  end
end
